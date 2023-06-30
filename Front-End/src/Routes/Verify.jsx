import React from 'react'

const Verify = () => {

  useEffect(() => { 
    axiosInstance.put("/verify", {
      params: {
        email: localStorage.getItem("email"),
      },
    })
}, [])

    const goLogin = () =>{
      localStorage.removeItem("email")
      window.location.href("/Login")
    }

  return (
    <div>
        <div className="accSucced logInContent">
          <h2>Cuenta verificada con éxito</h2>
          <p>¡Tu cuenta se ha verificado correctamente!</p>
          <button onClick={goLogin}>Ir a Login</button>
        </div>        
    </div>
  )
}

export default Verify