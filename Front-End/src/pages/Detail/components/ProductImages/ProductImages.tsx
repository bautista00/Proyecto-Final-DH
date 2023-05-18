import { ProductImagesContainer } from "./styled";

const ProductImages = ({ image }: { image: string }) => {
  return (
    <ProductImagesContainer>
      <img src={image} alt="product" />
    </ProductImagesContainer>
  );
};

export default ProductImages;
