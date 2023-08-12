import { render, screen } from "@testing-library/react"
import SendMoneyForm from "./SendMoneyForm"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))}))
describe('Send Money Form', () => {
    it(("form renders properly"),()=>{
        render(<SendMoneyForm></SendMoneyForm>)
        //Label
        expect(screen.getByText(/send money to/i)).toBeInTheDocument()
        expect(screen.getByText(/amount/i)).toBeInTheDocument()
        //Button
        expect(screen.getByRole("button",{name:/send money/i})).toBeInTheDocument()
        //Input fields
        expect(screen.getByLabelText(/send money to/i)).toHaveAttribute('type','email')
        expect(screen.getByLabelText(/amount/i)).toHaveAttribute('type','number')
    })
    //TODO button test
})