import { render } from "@testing-library/react";
import { screen } from "@testing-library/react";
import Transaction from "./Transaction";

describe("Transaction",()=>{
    it(("Transaction Renders Properly"),()=>{
        render(<Transaction amount={100} date="Sun Apr 02 03:38:53 IST 2023" destination="Cashback Transfer" type="Credit"></Transaction>);
        expect(screen.getByText(/100/i)).toBeInTheDocument()
        expect(screen.getByText(/Sun Apr 02/i) ).toBeInTheDocument() 
        expect(screen.getByText(/Cashback/i)).toBeInTheDocument()  
        expect(screen.getByText(/Credit/i)).toBeInTheDocument()
    })
    it(("Checking the color when type is Credit"),()=>{
        render(<Transaction amount={100} date={"Sun Apr 02 03:38:53 IST 2023"} destination={"mohitmg700@gmail.com"} type={"Credit"}></Transaction>);
        const element=screen.getByRole("heading",{name:"₹ 100.00"})
        expect(element).toHaveStyle({color:"green"})
    })
    it(("Checking the color when type is Debit"),()=>{
        render(<Transaction amount={100} date={"Sun Apr 02 03:38:53 IST 2023"} destination={"mohitmg700@gmail.com"} type={"Debit"}></Transaction>);
        const element=screen.getByRole("heading",{name:"₹ 100.00"})
        expect(element).toHaveStyle({color:"red"})
    })
})