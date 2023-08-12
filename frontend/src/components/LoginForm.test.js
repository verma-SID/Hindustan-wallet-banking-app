import { render } from "@testing-library/react"
import LoginPage from "../pages/LoginPage"
import { useNavigation } from "react-router-dom"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useActionData: jest.fn(() => ({})),
    useNavigate: jest.fn(() => ({})),
    useNavigation: jest.fn(() => ({})),
}))
describe('Login Form test', () => {
    it(("Form renders properly"),()=>{
        useNavigation.mockReturnValue({state:"submitting"})
        const{debug}=render(<LoginPage></LoginPage>)
        debug()
    })
})