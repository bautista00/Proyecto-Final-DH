import { useState } from "react";
import { axiosInstance } from "../config";
import CurrentBookings from "../Components/CurrentBookings";
import CompletedBookings from "../Components/CompletedBookings";
import { Card } from "antd";

const tabListNoTitle = [
  {
    key: "Vigentes",
    tab: "Vigentes",
  },
  {
    key: "Finalizadas",
    tab: "Finalizadas",
  },
];

const contentListNoTitle = {
  Vigentes: <CurrentBookings />,
  Finalizadas: <CompletedBookings />,
};

const BookingControl = () => {
  const [activeTabKey2, setActiveTabKey2] = useState("Vigentes");

  const onTab2Change = (key) => {
    setActiveTabKey2(key);
  };
  return (
    <div className="product-form-container">
      <h1 className="tituloHistorial">Reservas</h1>
        <div className="tableReservas">
          <Card
            tabList={tabListNoTitle}
            activeTabKey={activeTabKey2}
            onTabChange={onTab2Change}
          >
            {contentListNoTitle[activeTabKey2]}
          </Card>
        </div>
    </div>
  );
};

export default BookingControl;
