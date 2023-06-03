const UserInformation = ({ user }) => {
  return (
    <div className="user-information">
      <h1>Perfil</h1>

      <div className="form-group">
        <label htmlFor="nombre">Nombre:</label>
        <input type="text" id="Nombre" value={user?.name} />
      </div>

      <div className="form-group">
        <label htmlFor="apellido">Apellido:</label>
        <input type="text" id="Apellido" value={user?.lastname} />
      </div>

      <div className="form-group">
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" value={user?.email} />
      </div>

      <div className="form-group">
        <label htmlFor="telefono">Teléfono:</label>
        <input type="number" id="telefono" value={user?.phone} />
      </div>

      <div className="form-group">
        <label htmlFor="pais">País:</label>
        <input type="text" id="pais" value={user?.country} />
      </div>

      <div className="form-group">
        <label htmlFor="ciudad">Ciudad:</label>
        <input type="text" id="ciudad" value={user?.city} />
      </div>
    </div>
  );
};

export default UserInformation;
