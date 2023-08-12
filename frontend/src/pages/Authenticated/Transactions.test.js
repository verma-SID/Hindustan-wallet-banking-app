import { render, screen } from "@testing-library/react"
import Transactions from "./Transactions"
import { useLoaderData } from "react-router-dom"

//Mock the useLoaderData()
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))
}))

describe('Transactions Page tests', () => { 

    it(("Page renders Properly"),()=>{
        useLoaderData.mockReturnValue({transactionList:[]})
        render(<Transactions></Transactions>)
        expect(screen.getByText(/my transactions/i)).toBeInTheDocument()
    })

    it(("When there are No transactions"),()=>{
        useLoaderData.mockReturnValue({transactionList:[]})
        render(<Transactions></Transactions>)
        expect(screen.getByText(/no transactions yet/i)).toBeInTheDocument()
    })

    it(("When there transactions are available"),()=>{
        useLoaderData.mockReturnValue(
            {
                transactionList:[{id:"27363276244435", amount:100.00 ,date: "Sun Apr 02 03:38:53 IST 2023",destination: "mohitmg700@gmail.com",type: "Debit"}]
            }
        )
        render(<Transactions></Transactions>)

        //Verifing the Text
        expect(screen.getByText("100.00")).toBeInTheDocument()
        expect(screen.getByText("Sun Apr 02 03:38:53 IST 2023")).toBeInTheDocument()
        expect(screen.getByText("mohitmg700@gmail.com")).toBeInTheDocument()
        expect(screen.getByText("Debit")).toBeInTheDocument()
    })
})