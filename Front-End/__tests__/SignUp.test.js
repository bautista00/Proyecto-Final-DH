import { validarMail } from '../src/Routes/SignUp'

test("Validación de mail", () =>{
    const text = validarMail('estefy@mail.com')
    expect(text).toBeTruthy()
})