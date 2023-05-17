import { Button } from "./Button";
import { Logo } from "./Logo";
import { ButtonsContainer, HeaderContainer } from "./styled";

const Header = () => {
  return (
    <HeaderContainer>
      <Logo href="/" />
      <ButtonsContainer>
        <Button title="Crear cuenta" />
        <a href="/CreateProduct">
          <Button title="Iniciar sesíon" />
        </a>
      </ButtonsContainer>
    </HeaderContainer>
  );
};

export default Header;
