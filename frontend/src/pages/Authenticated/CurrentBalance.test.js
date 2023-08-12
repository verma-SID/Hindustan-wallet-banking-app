import {render, screen } from "@testing-library/react";
import CurrentBalance from "./CurrentBalance"
import { useLoaderData } from "react-router-dom";
jest.mock("react-router-dom", () => ({
    ...jest.requireActual("react-router-dom"),
    useLoaderData: jest.fn(() => ({}))}))
describe("Current balance page",()=>{
    it(("Page renders properly"),()=>{
        useLoaderData.mockReturnValue({
            resData1:{
                amount:100
            },
            resData2:{
                cardList:[{cardNumber:"4322 2442 1214 2321", bankName:"HDFC Bank", cardHolder:"Tushar Gupta"}]
            }
        })
        render(<CurrentBalance></CurrentBalance>)
        expect(screen.getByTestId("elements")).toBeInTheDocument()
    })
    //TODO reward message
})