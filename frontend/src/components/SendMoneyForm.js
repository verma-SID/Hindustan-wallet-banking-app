import Card from "./UI/Card";
import { useRef, useState } from "react";
import { useLoaderData } from "react-router-dom";
export default function SendMoneyForm() {
  //Reading the form values by the useRef() hook
  const ref1 = useRef();
  const ref2 = useRef();

  //taking url params from the loader function
  const loaderData = useLoaderData();

  const [warningMessage, setWarningMessage] = useState(null);
  const [isFailed, setIsFailed] = useState(null);

  //onClick Handler for Add Money Button
  async function sendMoneyHandler(event) {
    event.preventDefault();
    const moneyTransfer = {
      receiver: ref1.current.value,
      amount: +ref2.current.value,
    };
    console.log(moneyTransfer);
    const response = await fetch(
      `http://localhost:8080/user/${loaderData.id}/money-transfer`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(moneyTransfer),
      }
    );
    if (!(+response.status === 200)) {
      setWarningMessage("Failed");
      setIsFailed(true);
    } else {
      setWarningMessage("Money Successfully Sent");
      setIsFailed(false);
    }
    //Reload Page after 2 secs
    setTimeout(() => {
      window.location.reload(true);
    }, 2000);
  }
  return (
    <>
      <Card>
        <form>
          <div class="mb-3">
            <label for="receiver" class="form-label">
              Send Money To
            </label>
            <input
              ref={ref1}
              type="email"
              class="form-control"
              id="receiver"
              name="receiver"
              placeholder="Enter the Email of the Receiver"
              required
            ></input>
          </div>
          <div class="mb-3">
            <label for="amount" class="form-label">
              Amount
            </label>
            <input
              ref={ref2}
              type="number"
              class="form-control"
              id="amount"
              name="amount"
              placeholder="Enter the Amount"
              step="any"
              required
            ></input>
          </div>
          <button class="btn btn-primary" onClick={sendMoneyHandler}>
            Send Money
          </button>
        </form>
      </Card>
      {warningMessage && (
        <div className={`form-text ${isFailed ? "failed" : "success"}`}>
          {warningMessage}
        </div>
      )}
    </>
  );
}

//Form for sending the money