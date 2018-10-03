# JPX

[![Build Status](https://travis-ci.org/jenetics/jpx.svg?branch=master)](https://travis-ci.org/jenetics/jpx)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.jenetics/jpx/badge.svg)](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22jpx%22)
[![Javadoc](https://www.javadoc.io/badge/io.jenetics/jpx.svg)](http://www.javadoc.io/doc/io.jenetics/jpx)

**JPX** is a Java library for creating, reading and writing [GPS](https://en.wikipedia.org/wiki/Global_Positioning_System) data in [GPX](https://en.wikipedia.org/wiki/GPS_Exchange_Format) format. It is a *full* implementation of version [1.1](http://www.topografix.com/GPX/1/1/) and version [1.0](http://www.topografix.com/gpx_manual.asp) of the GPX format. The data classes are completely immutable and allows a functional programming style. They  are working also nicely with the Java 8 [Stream](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) API. It is also possible to convert the location information into strings which are compatible to the [ISO 6709](http://en.wikipedia.org/wiki/ISO_6709) standard.

Beside the basic functionality of reading and writing GPX files, the library also allows to manipulate the read GPX object in a functional way.


## Dependencies

No external dependencies are needed by the _JPX_ library. It only needs Java 8 to compile and run.


## Building JPX

For  building the JPX library you have to check out the master branch from Github.

    $ git clone https://github.com/jenetics/jpx.git
    
*Executing the tests:*
    
    $ cd jpx
    $ ./gradle test

*Building the library:*

    $ ./gradle jar
    

## Examples

### Creating new GPX object with 3 track-points

```java
final GPX gpx = GPX.builder()
    .addTrack(track -> track
        .addSegment(segment -> segment
            .addPoint(p -> p.lat(48.2081743).lon(16.3738189).ele(160))
            .addPoint(p -> p.lat(48.2081743).lon(16.3738189).ele(161))
            .addPoint(p -> p.lat(48.2081743).lon(16.3738189).ele(162))))
    .build();
```

**Writing GPX object to a file**

```java
GPX.write(gpx, "track.gpx");
```

*GPX output*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.1" creator="JPX - Java GPX library" xmlns="http://www.topografix.com/GPX/1/1">
    <trk>
        <trkseg>
            <trkpt lat="48.2081743" lon="16.3738189">
                <ele>160.0</ele>
            </trkpt>
            <trkpt lat="48.2081743" lon="16.3738189">
                <ele>161.0</ele>
            </trkpt>
            <trkpt lat="48.2081743" lon="16.3738189">
                <ele>162.0</ele>
            </trkpt>
        </trkseg>
    </trk>
</gpx>

```

### Reading GPX object from file

This example writes a given `GPX` object to a file, reads it again and prints the `WayPoint`s of all tracks and all track-segments to the console.

```java
GPX.write(gpx, "track.gpx");
GPX.read("gpx.xml").tracks()
    .flatMap(Track::segments)
    .flatMap(TrackSegment::points)
    .forEach(System.out::println);

```

*Console output*

```bash
$ [lat=48.2081743, lon=48.2081743, ele=160]
$ [lat=48.2081743, lon=48.2081743, ele=161]
$ [lat=48.2081743, lon=48.2081743, ele=162]

```

### Reading GPX 1.0 and writing GPX 1.1

By default, JPX is reading and writing the GPX files in version 1.1. But it is possible to read and write GPX files in version 1.0 as well.

```java
// Reading GPX 1.0 file.
final GPX gpx10 = GPX.reader(GPX.Version.V10).read("track-v10.gpx");

// Changing GPX version to 1.1.
final GPX gpx11 = gpx10.toBuilder()
	.version(GPX.Version.V11)
	.build();

// Writing GPX to file.
GPX.write(gpx11, "track-v11.gpx");
```

### ISO 6709 location strings

With the `LocationFormatter` class it is possible to create ISO 6709 compatible strings.

```java
final Point p = WayPoint.of(...);
final Location loc = Location.of(p);
final LocationFormatter format = LocationFormatter.ISO_HUMAN_LONG;
System.out.println(format.format(loc));
```
The printed location will look like this

    24°59'15.486"N 65°14'03.390"W 65.23m

It is also possible to define your own formatter from a given pattern string,

```java
final LocationFormatter format = 
    LocationFormatter.ofPattern("DD°MMSS dd°mmss");
```
which leads to the following output

    24°5915 65°1403

### Geodetic calculations

#### Distance between two points

```java
final Point start = WayPoint.of(47.2692124, 11.4041024);
final Point end = WayPoint.of(47.3502, 11.70584);
final Length distance = Geoid.WGS84.distance(start, end);
System.out.println(distance);
```

*Console output*

```bash
$ 24528.356073554987 m
```

#### Path length 

Calculate the path length of the first track-segment.

```java
final Length length = gpx.tracks()
    .flatMap(Track::segments)
    .findFirst()
    .map(TrackSegment::points).orElse(Stream.empty())
    .collect(Geoid.WGS84.toPathLength());
```

### GPX manipulation/filtering

#### Filtering

The following example filters empty tracks and track-segments from an existing `GPX` object.
	
```java
final GPX gpx = GPX.read("track.gpx");

// Filtering empty tracks.
final GPX gpx1 = gpx.toBuilder()
    .trackFilter()
        .filter(Track::nonEmpty)
        .build()
    .build();

// Filtering empty track-segments.
final GPX gpx2 = gpx.toBuilder()
    .trackFilter()
        .map(track -> track.toBuilder()
            .filter(TrackSegment::nonEmpty)
            .build())
        .build()
    .build();

// Filtering empty tracks and track-segments.
final GPX gpx3 = gpx.toBuilder()
    .trackFilter()
        .map(track -> track.toBuilder()
            .filter(TrackSegment::nonEmpty)
            .build())
        .filter(Track::nonEmpty)
        .build()
    .build();
```

#### Changing GPX object

*Fixing* the time of all track way-points by adding one hour.

```java
final GPX gpx = GPX.read("track.gpx");

final GPX gpx1 = gpx.toBuilder()
    .trackFilter()
        .map(track -> track.toBuilder()
            .map(segment -> segment.toBuilder()
                .map(wp -> wp.toBuilder()
                    .time(wp.getTime()
                        .map(t -> t.plusHours(1))
                        .orElse(null))
                    .build())
                .build())
            .build())
        .build()
    .build();
```

Doing the same only for the GPX way-points.

```java
final GPX gpx = GPX.read("track.gpx");

final GPX gpx1 = gpx.toBuilder()
    .wayPointFilter()
        .map(wp -> wp.toBuilder()
            .time(wp.getTime()
                .map(t -> t.plusHours(1))
                .orElse(null))
            .build())
        .build()
    .build();
```

## License

The library is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

    Copyright 2016-2018 Franz Wilhelmstötter

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Release notes

### [1.4.0](https://github.com/jenetics/jpx/releases/tag/v1.4.0)

#### Enhancement

* [#65](https://github.com/jenetics/jpx/issues/65): Make it compatible with JSR-173 stax-api 1.0.1.
* [#70](https://github.com/jenetics/jpx/issues/70): ISO 6709 string representation for GPS coordinate


