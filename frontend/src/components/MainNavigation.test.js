import {fireEvent, render, screen } from "@testing-library/react"
import MainNavigation from "./MainNavigation"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useNavigate: jest.fn(() => ({}))}))
describe("Main NavBar Tests",()=>{
    //Check if render properly
    it(("renders properly"),()=>{
        render(<MainNavigation></MainNavigation>)
        expect(screen.getByText(/hindustanpe/i)).toBeInTheDocument()
        expect(screen.getByText(/individual/i)).toBeInTheDocument()
        expect(screen.getByText(/business/i)).toBeInTheDocument()
        expect(screen.getByText(/partners/i)).toBeInTheDocument()
        expect(screen.getByText(/partner with us/i)).toBeInTheDocument()
        expect(screen.getByText(/partner directory/i)).toBeInTheDocument()
        expect(screen.getByText(/policies/i)).toBeInTheDocument()
        expect(screen.getByRole("button",{name:"Login"})).toBeInTheDocument()
        expect(screen.getByRole("button",{name:"Signup"})).toBeInTheDocument()
    })
    // it(("Login Button test"),()=>{
    //     render(<MainNavigation></MainNavigation>)
    //     fireEvent.click(screen.getByRole("button",{name:"Login"}))
    //     //todo
    // })

        // it(("Signup Button test"),()=>{
    //     render(<MainNavigation></MainNavigation>)
    //     fireEvent.click(screen.getByRole("button",{name:"Signup"}))
    //     //todo
    // })
})