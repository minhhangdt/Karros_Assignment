package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Track;
import org.alternativevision.gpx.beans.Waypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.domain.Gps;
import com.example.demo.domain.Metadata;
import com.example.demo.domain.TrackPoint;
import com.example.demo.domain.WayPoint;
import com.example.demo.domain.dto.GpxDTO;
import com.example.demo.repository.GpsRepository;
import com.example.demo.repository.TrackRepository;


@Service
public class GpsSerivceImpl implements GpsService {

	@Autowired
	GpsRepository gpsRepository;

	@Autowired
	TrackRepository trackRepository;

	@Override
	public List<TrackPoint> getLastestList(int top) {
		Pageable pageable = new PageRequest(0, top);
		List<TrackPoint> trackPointLastestList = trackRepository.getLastestList(pageable);
		return trackPointLastestList;
	}

	@Override
	public Gps getDetail(long id) {
		return gpsRepository.findOneById(id);
	}

	@Override
	public Gps saveDataFromGpx(MultipartFile file) throws Exception {
		Gps gps = new Gps();
		Metadata metadata = getMetadataToGps(file, gps);
		InputStream input = file.getInputStream();
		GPXParser parser = new GPXParser();
		GPX gpx = parser.parseGPX(input);	
		
		List<TrackPoint> trackPoints = new ArrayList<>();
		if (gpx.getTracks().size() == 1) {
			Track first = (Track) gpx.getTracks().toArray()[0];
			getTrackPointList(gps, trackPoints, first);
		} else {
			for (Track track : gpx.getTracks()) {
				getTrackPointList(gps, trackPoints, track);
			}
		}

		List<WayPoint> wayPoints = new ArrayList<>();
		getWayPointList(gps, gpx, wayPoints);
		
		gps.setTrackPoint(trackPoints);
		gps.setWayPoint(wayPoints);
		gps.setMetadata(metadata);
		return gpsRepository.save(gps);
	}

	private void getWayPointList(Gps gps, GPX gpx, List<WayPoint> wayPoints) {
		for (Waypoint wayPointGpx : gpx.getWaypoints()) {
			WayPoint wp = new WayPoint();
			wp.setLatitude(wayPointGpx.getLatitude());
			wp.setLongitude(wayPointGpx.getLongitude());
			wp.setName(wayPointGpx.getName());
			wp.setSym(wayPointGpx.getSym());
			wp.setGps(gps);
			wayPoints.add(wp);
		}
	}

	private Metadata getMetadataToGps(MultipartFile file, Gps gps) throws IOException, JAXBException {
		InputStream input = file.getInputStream();
		JAXBContext jaxbContext = JAXBContext.newInstance(GpxDTO.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		GpxDTO gpxDTO = (GpxDTO) jaxbUnmarshaller.unmarshal(input);
		Metadata metadata = new Metadata(gps);
		metadata.setName(gpxDTO.getMetadata().getName());
		metadata.setDesc(gpxDTO.getMetadata().getDesc());
		metadata.setTime(gpxDTO.getMetadata().getTime());
		return metadata;
	}

	private void getTrackPointList(Gps gps, List<TrackPoint> trackPoints, Track first) {
		for (Waypoint wpGpx : first.getTrackPoints()) {
			TrackPoint trackPoint = new TrackPoint();
			trackPoint.setLatitude(wpGpx.getLongitude());
			trackPoint.setLongitude(wpGpx.getLongitude());
			trackPoint.setEle(wpGpx.getElevation());
			trackPoint.setTime(wpGpx.getTime());
			trackPoint.setGps(gps);
			trackPoints.add(trackPoint);
		}
	}

}
