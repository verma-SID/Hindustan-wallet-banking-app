import { fireEvent, getByRole, getByText, render, screen } from "@testing-library/react"
import DebitCardForm from "./DebitCardForm"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))}))
describe('Debit Card Form Tests', () => { 
    it(("form renders properly"),()=>{
        render(<DebitCardForm></DebitCardForm>)
        //Label
        expect(screen.getByText(/Card Number/i)).toBeInTheDocument()
        expect(screen.getByText(/Bank Name/i)).toBeInTheDocument()
        expect(screen.getByText(/Card Holder Name/i)).toBeInTheDocument()
        //Button
        expect(screen.getByRole("button",{name:"Add Card"})).toBeInTheDocument()
        //Input fields
        expect(screen.getByLabelText(/Card Number/i)).toHaveAttribute('type','tel')
        expect(screen.getByLabelText(/Bank Name/i)).toHaveAttribute('type','text')
        expect(screen.getByLabelText(/Card Holder Name/i)).toHaveAttribute('type','text')
    })
    //TODO: Test the button
    it("Add Card Button Test", () => {
        render(<DebitCardForm></DebitCardForm>)
    
        fireEvent.change(screen.getByLabelText("Card Number"), {
          target: { value: "1323 4322 1312 4645" },
        });
        fireEvent.change(screen.getByLabelText("Bank Name"), { target: { value: "Canara Bank" } });
        fireEvent.change(screen.getByLabelText("Card Holder Name"), { target: { value: "Tushar Gupta" } });
        //TODO check button
    });
 })