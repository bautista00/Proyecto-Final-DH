import { Footer } from "./components/Footer";
import { Header } from "./components/Header";
import { LayoutProps } from "./types";

export const Layout = ({ children }: LayoutProps) => {
  return (
    <>
      <Header />
      <main>{children}</main>
      <Footer />
    </>
  );
};
