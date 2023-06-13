import { Button, Select, Space, Table } from "antd";

const columns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "name",
    render: (text) => <a>{text}</a>,
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },

  {
    title: "Permisos",
    key: "permisos",
    dataIndex: "permisos",
    render: (_, { tags }) => (
      <>
        <a href="/UsersPermissions">
          <Button>Permisos</Button>
        </a>
      </>
    ),
  },
  {
    title: "Rol",
    key: "rol",
    render: (_, record) => {
      return (
        <Space size="middle">
          <Select
            placeholder="Seleccione un rol"
            options={[
              {
                value: "jack",
                label: "Administrador",
              },
              {
                value: "lucy",
                label: "Arrendador",
              },
              {
                value: "tom",
                label: "Usuario",
              },
            ]}
          />
          <a>Eliminar</a>
        </Space>
      );
    },
  },
];
const data = [
  {
    key: "1",
    name: "Joaquin Presh",
    email: "joaquinpresh@gmail.com",
    address: "Buenos Aires, Argentina",
    permisos: "Permisos",
  },
  {
    key: "2",
    name: "Juan david dominguez",
    email: "juandavid@gmail.com",
    address: "Cali, Colombia",
    permisos: "Permisos",
  },

  {
    key: "3",
    name: "Alejandro Tornell",
    email: "alejandrotornell@gmail.com",
    address: "Buenos Aires, Argentina",
    permisos: "Permisos",
  },

  {
    key: "4",
    name: "Sofia Castro",
    email: "sofiacastro@gmail.com",
    address: "Cali, Colombia",
    permisos: "Permisos",
  },
];

const Users = () => {
  return (
    <>
      <div className="usersButton">
        <Button>Guardar</Button>
        <Button>Cancelar</Button>
      </div>
      <Table columns={columns} dataSource={data} />
    </>
  );
};

export default Users;
