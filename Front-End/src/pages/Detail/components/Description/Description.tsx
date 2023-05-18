import {
  DescriptionContainer,
  DescriptionText,
  DescriptionTitle,
} from "./styled";

const Description = ({
  description,
  schedule,
}: {
  description: string;
  schedule: string;
}) => {
  return (
    <DescriptionContainer>
      <DescriptionTitle>Descripción</DescriptionTitle>
      <hr />
      <DescriptionText>
        {description} {schedule}
      </DescriptionText>
    </DescriptionContainer>
  );
};

export default Description;
