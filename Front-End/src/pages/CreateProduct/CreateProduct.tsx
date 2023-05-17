import { Layout } from "../../common/layout";
import { ProductForm } from "./components/ProductForm";
import { CreateProductContainer } from "./styled";

const CreateProduct = () => {
  return (
    <Layout>
      <CreateProductContainer>
        <h1>Agregar Producto</h1>
        <ProductForm />
      </CreateProductContainer>
    </Layout>
  );
};

export default CreateProduct;
