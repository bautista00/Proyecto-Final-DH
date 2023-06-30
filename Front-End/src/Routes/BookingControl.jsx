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
  const [activeTabKey2, setActiveTabKey2] = useState("User");

  const onTab2Change = (key) => {
    setActiveTabKey2(key);
  };
  return (
    <div>
      <div>
        <div>
          <Card
            style={{
              width: "50%",
            }}
            tabList={tabListNoTitle}
            activeTabKey={activeTabKey2}
            tabBarExtraContent={<a href="#">More</a>}
            onTabChange={onTab2Change}
          >
            {contentListNoTitle[activeTabKey2]}
          </Card>
        </div>
      </div>
    </div>
  );
};

export default BookingControl;
