import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { ProductDetailContainer, ProductDetailWrapper } from "./styled";
import { Layout } from "../../common/layout";
import { LocationHeader } from "./components/LocationHeader";
import { BackButton } from "./components/BackButton";
import { Controls } from "./components/Controls";
import { ProductImages } from "./components/ProductImages";
import { Description } from "./components/Description";

const Detail = () => {
  const [b, setB] = useState<any>([]);

  const { id } = useParams();
  const idNumber = parseInt(id!);

  useEffect(() => {
    fetch("../data/data.json")
      .then((response) => response.json())
      .then((data) => setB(data));
  }, [id]);

  return (
    <Layout>
      <ProductDetailContainer>
        <BackButton href="/" />
        <LocationHeader location={b[idNumber]?.location} />
        <Controls />
        <ProductDetailWrapper>
          <ProductImages image={b[idNumber]?.image} />
          <Description
            description={b[idNumber]?.description}
            schedule={b[idNumber]?.schedule}
          />

          <div>
            <p>{b[idNumber]?.price}</p>
            <p>{b[idNumber]?.services}</p>
            <p>{b[idNumber]?.contact}</p>
          </div>
        </ProductDetailWrapper>
      </ProductDetailContainer>
    </Layout>
  );
};

export default Detail;
