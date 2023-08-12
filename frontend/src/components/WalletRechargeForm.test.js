import { fireEvent, getByText, render, screen } from "@testing-library/react"
import WalletRechargeForm,{walletRechargeHandler} from "./WalletRechargeForm"
import { useLoaderData } from "react-router-dom"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))}))
global.fetch=jest.fn(()=>{
    Promise.resolve({
        
    })
})
describe('Wallet Recharge Form test', () => { 
    it(("form render properly with having no card"),()=>{
        useLoaderData.mockReturnValue({
            resData2:{
                cardList:[]
            }
        })
        render(<WalletRechargeForm></WalletRechargeForm>)
        //Checking labels and Button
        expect(screen.getByText(/Choose Linked card/i)).toBeInTheDocument()
        expect(screen.getByText(/Amount/i)).toBeInTheDocument()
        expect(screen.getByRole("button",{name:"Add Money"})).toBeInTheDocument()
        //Checking of the Warning Add Card text
        expect(screen.getByText(/please add debit card/i)).toBeInTheDocument()
        //Checking amount input field
        expect(screen.getByLabelText(/amount/i)).toHaveAttribute('type','number')
        //Checking if button is disabled
        expect(screen.getByRole("button",{name:"Add Money"})).toBeDisabled()
    })
    it(("form render properly with having some card"),()=>{
        useLoaderData.mockReturnValue({
            resData2:{
                cardList:[{cardNumber:"2134 1321 1234 4543",bankName:"Canara Bank",cardHolder:"Tushar Gupta"}]
            }
        })
        render(<WalletRechargeForm></WalletRechargeForm>)
        //Checking the Option
        const cardOption=screen.getByRole("option",{name:"Canara Bank Debit Card xxxx 4543"})
        expect(cardOption).toBeInTheDocument()
        //Checking if button is not disabled
        expect(screen.getByRole("button",{name:"Add Money"})).not.toBeDisabled()
    })
    //TODO: check button
    it("should display a success message ", async() => {
        useLoaderData.mockReturnValue({
            resData2:{
                cardList:[{cardNumber:"2134 1321 1234 4543",bankName:"Canara Bank",cardHolder:"Tushar Gupta"}]
            }
        })
        render(<WalletRechargeForm></WalletRechargeForm>)
        fireEvent.change(screen.getByLabelText("Choose Linked Card"), {
          target: { value: "Canara Bank Debit Card xxxx 4543" },
        });
        fireEvent.change(screen.getByLabelText("Amount"), { target: { value: "50" } });
        expect(screen.getByLabelText("Amount")).toHaveValue(50)
        expect(screen.getByLabelText("Choose Linked Card")).toHaveValue("Canara Bank Debit Card xxxx 4543")
        // fireEvent.click(screen.getByText("Add Money"));
        //todo button test
        
      });
 })