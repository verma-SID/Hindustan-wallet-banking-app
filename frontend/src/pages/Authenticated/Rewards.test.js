import { render, screen } from "@testing-library/react"
import Rewards from "./Rewards"
import { useLoaderData } from "react-router-dom"

//Mock the useLoaderData()
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))
}))

describe('Rewards Page test', () => {
    it(("Page Renders Properly"),()=>{
        useLoaderData.mockReturnValue({resData:{amount:0,rewardList:[]}})
        render(<Rewards></Rewards>)
        //check heading
        expect(screen.getByText(/my rewards/i)).toBeInTheDocument()
        //check button
        expect(screen.getByRole("button",{name:/transfer to wallet/i})).toBeInTheDocument()
    })
    it(("if there are no rewards"),()=>{
        useLoaderData.mockReturnValue({resData:{amount:0,rewardList:[]}})
        render(<Rewards></Rewards>)
        //check the amount
        expect(screen.getByText(/0.00/i)).toBeInTheDocument()
        //check the no reward text
        expect(screen.getByText(/no rewards yet/i)).toBeInTheDocument()
        //check the button in disabled
        expect(screen.getByRole("button",{name:/transfer to wallet/i})).toBeDisabled()
    })
    it(("if there are some rewards"),()=>{
        useLoaderData.mockReturnValue({resData:{amount:10,rewardList:[{id:"435344353", amount:10,date:"xyz"}]}})
        render(<Rewards></Rewards>)
        //check the rewads by the date
        expect(screen.getByText(/xyz/i)).toBeInTheDocument()
        //check the button is not disabled
        expect(screen.getByRole("button",{name:/transfer to wallet/i})).not.toBeDisabled()
    })
    //TODO check the button
})