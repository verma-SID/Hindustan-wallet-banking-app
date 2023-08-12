import { render } from "@testing-library/react"
import Balance from "./Balance"
import { screen } from "@testing-library/react"

describe("Balance",()=>{
    it(("When Balance is a floating number with 2 decimal points"),()=>{
        render(<Balance amount={10.23}></Balance>)
        expect(screen.getByText("10.23")).toBeInTheDocument()
    })
    it(("When Balance is a floating number with more than 2 decimal points"),()=>{
        render(<Balance amount={10.232}></Balance>)
        expect(screen.getByText("10.23")).toBeInTheDocument()
    })
})