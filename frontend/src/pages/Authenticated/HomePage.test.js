import { fireEvent, render, screen } from "@testing-library/react"
import HomePage from "./HomePage"
import { useLoaderData } from "react-router-dom"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))}))
describe("Home Page test",()=>{
    it(("Page renders properly"),()=>{
        useLoaderData.mockReturnValue(
            {
                firstName:"Tushar",
                lastName:"Gupta"
            }
        )
        render(<HomePage></HomePage>)
        //checking the elements
        expect(screen.getByTestId(/greeting/i)).toBeInTheDocument()
        expect(screen.getByTestId(/features/i)).toBeInTheDocument()
        expect(screen.getByTestId(/instruments/i)).toBeInTheDocument()
    })
    it(("Name on the Page renders properly"),()=>{
        useLoaderData.mockReturnValue(
            {
                firstName:"Tushar",
                lastName:"Gupta"
            }
        )
        render(<HomePage></HomePage>)
        //Greeting message name
        expect(screen.getByRole("heading",{name:/tushar gupta/i})).toBeInTheDocument()
    })
})