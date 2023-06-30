const UserInformation = ({ user }) => {






  
  return (
    <div className="user-information">
      <h1>Perfil</h1>

      <div className="form-group">
        <label htmlFor="nombre">Nombre:</label>
        <input type="text" id="Nombre" value={user?.nombre} />
      </div>

      <div className="form-group">
        <label htmlFor="apellido">Apellido:</label>
        <input type="text" id="Apellido" value={user?.apellido} />
      </div>

      <div className="form-group">
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" value={user?.email} />
      </div>

      <div className="form-group">
        <label htmlFor="telefono">Teléfono:</label>
        <input type="number" id="telefono" value={user?.telefono} />
      </div>
    </div>
  );
};

export default UserInformation;
