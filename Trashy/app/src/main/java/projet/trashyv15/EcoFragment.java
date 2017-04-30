package projet.trashyv15;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EcoFragment extends Fragment implements OnMapReadyCallback {


    private View view;

    Button accedCarte;
    //variables pour la carte
    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private ZoomControls zoom;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;

    //variable pour localisation:
    //ajout des latLng pour tous les ecocentres
    LatLng Cdn = new LatLng(45.505244,-73.638851);
    LatLng Acadie = new LatLng(45.539804,-73.676426);
    LatLng lpp = new LatLng(45.534058,-73.592329);
    LatLng stmich = new LatLng(45.559818,-73.615919);
    LatLng mtroy = new LatLng(45.502573,-73.574698);

    LatLng[] points = new LatLng[]{Cdn,Acadie,lpp,stmich,mtroy};

    Marker mCdn,mstmich,mAcadie,mmtroy,mlpp;


    LatLng loc;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_eco, container, false);

        accedCarte = (Button) view.findViewById(R.id.accedCarte);


        accedCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.



                    LocationManager locationManager = (LocationManager)
                            getActivity().getSystemService(getContext().LOCATION_SERVICE);
                    Criteria criteria = new Criteria();

                    Location location = locationManager.getLastKnownLocation(locationManager
                            .getBestProvider(criteria, false));

                    if(location!=null){
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        loc = new LatLng(latitude,longitude);

                        LatLng pC = plusCourt(loc,points);

                        CameraPosition pCD = CameraPosition.builder().target(pC).zoom(12).bearing(0).tilt(45).build();
                        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(pCD));

                        if(pC.equals(Cdn)){
                            mCdn.showInfoWindow();
                        }
                        else if(pC.equals(lpp)){
                            mlpp.showInfoWindow();
                        }
                        else if(pC.equals(mtroy)){
                            mmtroy.showInfoWindow();
                        }
                        else if(pC.equals(Acadie)){
                            mAcadie.showInfoWindow();
                        }
                        else if(pC.equals(stmich)){
                            mstmich.showInfoWindow();
                        }


                    }
                    else{
                        Toast.makeText(getContext(), R.string.noLoc ,Toast.LENGTH_LONG).show();
                    }

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
                    }
                }

        }
        });


        //zoom
        zoom = (ZoomControls) view.findViewById(R.id.zcZoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomOut());

            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.title_ecoc);

        mMapView = (MapView) view.findViewById(R.id.frame_layout).findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    // Pour la carte
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //add marker?

        CameraPosition current = CameraPosition.builder().target(new LatLng(45.5016889,-73.56725599999999)).zoom(11).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(current));





        mCdn = mGoogleMap.addMarker(new MarkerOptions()
                .position(Cdn)
                .title("6925 Ch De La Cote Des Neiges\n" +
                        "(514) 872-0384"));

        mAcadie = mGoogleMap.addMarker(new MarkerOptions()
                .position(Acadie)
                .title("1200 Henri-Bourassa Blvd W\n" +
                        "(514) 872-0384"));

        mlpp = mGoogleMap.addMarker(new MarkerOptions()
                .position(lpp)
                .title("1100 Rue des Carrières\n" +
                        "(514) 872-7706"));

        mstmich = mGoogleMap.addMarker(new MarkerOptions()
                .position(stmich)
                .title("2475 Rue des Regrattiers\n" +
                        "(514) 872-0384"));

        mmtroy = mGoogleMap.addMarker(new MarkerOptions()
                .position(mtroy)
                .title("2200 Rue Mansfield\n" +
                        "(514) 844-2000"));




        //localisation
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //mGoogleMap.setMyLocationEnabled(true);


            LocationManager locationManager = (LocationManager)
                    getActivity().getSystemService(getContext().LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            if(location!=null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                loc = new LatLng(latitude,longitude);
            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }




    }

    private LatLng plusCourt(LatLng loc, LatLng[] points) {
        LatLng pCourt = points[0];
        Double pCourtD = (loc.latitude - points[0].latitude) * (loc.latitude - points[0].latitude);
        pCourtD += (loc.longitude - points[0].longitude) * (loc.longitude - points[0].longitude);

        for(int i = 1;i<points.length;i++){
            Double dist = (loc.latitude - points[i].latitude) * (loc.latitude - points[i].latitude);
            dist += (loc.longitude - points[i].longitude) * (loc.longitude - points[i].longitude);

            if(dist<pCourtD) {
                pCourtD=dist;
                pCourt = points[i];
            }
        }
        Toast.makeText(getContext(),"" +pCourt.latitude +" "+ pCourt.longitude,Toast.LENGTH_LONG).show();

        return pCourt;
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getContext(), R.string.toastLoc, Toast.LENGTH_LONG).show();
                    accedCarte.setVisibility(View.GONE);
                    mMapView.setVisibility(View.GONE);
                }
                break;
        }
    }
}
