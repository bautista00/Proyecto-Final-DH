import { DownOutlined, DribbbleOutlined } from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Button, Dropdown, Space, DatePicker, TimePicker } from "antd";
import {
  FilterContainer,
  FilterTitle,
  FiltersButtonsContainer,
} from "./styled";
import dayjs from "dayjs";
import { useState } from "react";
import { MenuItemType } from "antd/es/menu/hooks/useItems";

const items: MenuItemType[] = [
  {
    label: "Futbol",
    key: "1",
    icon: <DribbbleOutlined />,
  },
  {
    label: "Tenis",
    key: "2",
    icon: <DribbbleOutlined />,
  },
  {
    label: "Basquetbol",
    key: "3",
    icon: <DribbbleOutlined />,
  },
  {
    label: "Voleibol",
    key: "4",
    icon: <DribbbleOutlined />,
  },
];

const Filters: React.FC = () => {
  const format = "HH:mm";
  const [item, setItem] = useState<MenuItemType | null>(null);

  const handleMenuClick: MenuProps["onClick"] = (e) => {
    console.log("click", e);
    const selectedItem = items.find((item) => item?.key === e.key) || null;
    setItem(selectedItem);
  };

  const menuProps = {
    items,
    onClick: handleMenuClick,
  };

  return (
    <FilterContainer>
      <FilterTitle>Encuentra tu cancha ideal</FilterTitle>
      <FiltersButtonsContainer>
        <Dropdown menu={menuProps}>
          <Button>
            <Space>
              {item ? `Deporte: ${item?.label}` : "Seleccione"}
              <DownOutlined />
            </Space>
          </Button>
        </Dropdown>
        <DatePicker />
        <TimePicker defaultValue={dayjs("12:08", format)} format={format} />
        <Button>Buscar canchas</Button>
      </FiltersButtonsContainer>
    </FilterContainer>
  );
};

export default Filters;
