import {  render } from "@testing-library/react";
import Reward from "./Reward";
import { screen } from "@testing-library/react";

describe("Reward",()=>{
    it(("Reward renders properly"),()=>{
        render(<Reward amount={100.0} date={"Sun Apr 02 03:38:53 IST 2023"}></Reward>);  
        expect(screen.getByText("100.00")).toBeInTheDocument()
        expect(screen.getByText("Sun Apr 02 03:38:53 IST 2023")).toBeInTheDocument()
    })
    it(("Reward renders properly with amount having more than 2 decimal points"),()=>{
        render(<Reward amount={100.123} date={"Sun Apr 02 03:38:53 IST 2023"}></Reward>);
        expect(screen.getByText("100.12")  ).toBeInTheDocument()
        expect(screen.getByText("Sun Apr 02 03:38:53 IST 2023") ).toBeInTheDocument()
    })
})