import { findByTestId, fireEvent, render, screen} from "@testing-library/react";
import DebitCard from "./DebitCard";
import { useLoaderData } from "react-router-dom";
jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useLoaderData: jest.fn(() => ({}))}))
describe("DebitCard",()=>{
    it(("If DebitCard renders properly"),()=>{
        render(<DebitCard bank="HDFC Bank" card="1234 5323 1231 5443" name="Tushar Gupta"></DebitCard>);
        expect(screen.getByText("HDFC Bank")).toBeInTheDocument();
        expect(screen.getByText("Card Number: 1234 5323 1231 5443")).toBeInTheDocument()
        expect(screen.getByText(/delete/i)).toBeInTheDocument()
    })
    it(("Testing the Delete Button Press"),()=>{
        render(<DebitCard bank="HDFC Bank" card="1234 5323 1231 5443" name="Tushar Gupta"></DebitCard>);
        const deleteButton=screen.getByText(/delete/i)
        fireEvent.click(deleteButton)
        //TODO check delete button
    })
})