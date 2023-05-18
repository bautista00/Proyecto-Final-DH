import { EnvironmentFilled } from "@ant-design/icons";
import { LocationHeaderContainer, LocationHeaderText } from "./styled";

const LocationHeader = ({ location }: { location: string }) => {
  return (
    <LocationHeaderContainer>
      <LocationHeaderText>
        <EnvironmentFilled /> {location}
      </LocationHeaderText>
    </LocationHeaderContainer>
  );
};

export default LocationHeader;
