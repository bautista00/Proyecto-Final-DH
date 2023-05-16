import { Layout } from "../../common/layout";
import FieldTypeSearch from "./components/FieldTypeSearch/FieldTypeSearch";
import { Filters } from "./components/Filters";
import Recommended from "./components/Recommended/Recommended";
import Slider from "./components/Slider/Slider";
import { HomeContainer } from "./styled";

export default function Home() {
  return (
    <Layout>
      <HomeContainer>
        <Slider />
        <Filters />
        <FieldTypeSearch />
        <Recommended />
      </HomeContainer>
    </Layout>
  );
}
