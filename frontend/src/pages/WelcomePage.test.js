import { fireEvent, render, screen } from "@testing-library/react"
import WelcomePage from "./WelcomePage"
import { useNavigate } from "react-router-dom"
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useNavigate: jest.fn(() => ({}))}))
describe("Welcome Page test",()=>{
    it(("Name on the Page renders properly"),()=>{
        render(<WelcomePage></WelcomePage>)
        expect(screen.getByTestId(/features/i)).toBeInTheDocument()
        expect(screen.getByTestId(/instruments/i)).toBeInTheDocument()
    })
})