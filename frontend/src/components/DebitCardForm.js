import { useRef, useState } from "react";
import { useLoaderData } from "react-router-dom";
import Card from "./UI/Card";

export default function DebitCardForm() {
  const ref1 = useRef();
  const ref2 = useRef();
  const ref3 = useRef();

  //taking url params from the loader function
  const loaderData = useLoaderData();

  const [warningMessage, setWarningMessage] = useState(null);
  const [isFailed, setIsFailed] = useState(null);

  //onClick Handler for Add Money Button
  async function addCardHandler(event) {
    event.preventDefault();
    const cardDetails = {
      cardNumber: ref1.current.value,
      bankName: ref2.current.value,
      cardHolder: ref3.current.value,
    };
    const response = await fetch(
      `http://localhost:8080/user/${loaderData.id}/add-card`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(cardDetails),
      }
    );
    console.log(+response.status);
    if (!(+response.status === 201)) {
      setWarningMessage("Failed");
      setIsFailed(true);
    } else {
      setWarningMessage("Card Successfully Added");
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
          <div className="col-md-12">
            <label htmlFor="validationDefault01" className="form-label">
              Card Number
            </label>
            <input
              ref={ref1}
              id="validationDefault01"
              className="form-control"
              type="tel"
              name="cardNumber"
              inputMode="numeric"
              pattern="[0-9\s]{13,19}"
              autoComplete="cc-number"
              maxLength="19"
              placeholder="xxxx xxxx xxxx xxxx"
              required
            ></input>
          </div>
          <div className="col-md-12">
            <label htmlFor="validationDefault02" className="form-label">
              Bank Name
            </label>
            <input
              ref={ref2}
              type="text"
              className="form-control"
              id="validationDefault02"
              name="bankName"
              placeholder="Bank Name"
              required
            />
          </div>
          <div className="col-md-12">
            <label htmlFor="validationCardHolder" className="form-label">
              Card Holder Name
            </label>
            <input
              ref={ref3}
              type="text"
              className="form-control"
              id="validationCardHolder"
              placeholder="Card Holder Name"
              name="cardHolder"
              required
            />
          </div>
          <div className="col-md-12" style={{ marginTop: "1rem" }}>
            <button className="btn btn-primary" onClick={addCardHandler}>
              Add Card
            </button>
          </div>
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
