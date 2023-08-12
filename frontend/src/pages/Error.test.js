import { getByText, render, screen } from "@testing-library/react"
import Error from "./Error"
describe('Error page', () => {
    it(("page renders properly"),()=>{
        render(<Error></Error>)
        expect(screen.getByText(/oops! error occured/i))
        expect(screen.getByText(/something went wrong/i))  
    })
})