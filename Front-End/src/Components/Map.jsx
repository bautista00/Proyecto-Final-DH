import React from "react";
import { MapContainer, TileLayer, Marker } from "react-leaflet";

function Map() {
  return (
    <MapContainer center={[0, 0]} zoom={2} style={{ height: "2vw", width: "86%" }}>
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
        
    </MapContainer>
  );
}

export default Map;
