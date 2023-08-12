import { render, screen } from "@testing-library/react"
import LinkedCards from "./LinkedCards"
import { useLoaderData } from "react-router-dom"

//Mock the useLoaderData()
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))
}))

describe('Linked Cards Test', () => {
    it(("Page renders Properly"),()=>{
        useLoaderData.mockReturnValue(
            {
                resData:{
                    cardList:[]
                }
            }
        )
        render(<LinkedCards></LinkedCards>)
        expect(screen.getByTestId("heading")).toBeInTheDocument()
        expect(screen.getByTestId("img-form")).toBeInTheDocument()
        expect(screen.getByTestId("cards")).toBeInTheDocument()
        expect(screen.getByText(/your cards/i)).toBeInTheDocument()
        expect((screen.getByRole("heading",{name:"Add Card"}))).toBeInTheDocument()
    })
    it(("When No cards Linked"),()=>{
        useLoaderData.mockReturnValue(
            {
                resData:{
                    cardList:[]
                }
            }
        )
        render(<LinkedCards></LinkedCards>)
        expect((screen.getByText(/no cards present/i))).toBeInTheDocument()
    })
    it(("When some card Linked"),()=>{
        useLoaderData.mockReturnValue(
            {
                resData:{
                    cardList:[{cardNumber:"2344 2131 5345 2311", bankName:"HDFC Bank", cardHolder:"Tushar Gupta"}]
                }
            }
        )
        render(<LinkedCards></LinkedCards>)
        expect((screen.getByText(/hdfc bank/i))).toBeInTheDocument()
    })
})