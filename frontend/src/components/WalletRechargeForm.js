import { useRef, useState } from "react";
import { useLoaderData } from "react-router-dom";
import Card from "./UI/Card";
export default function WalletRechargeForm(props) {
  //taking form values form useref()
  const ref1 = useRef();
  const ref2 = useRef();
  const loaderData = useLoaderData(); //taking url params from the loader fucntion
  const cards=[...loaderData.resData2.cardList]
  const [warningMessage, setWarningMessage] = useState(null);
  const [isFailed, setIsFailed] = useState(null);

  //onClick Handler for Add Money Button
  async function walletRechargeHandler(event) {
    event.preventDefault();
    const walletRecharge = {
      card: ref1.current.value,
      amount: +ref2.current.value,
    };
    console.log(walletRecharge);

    //making the HTTP request
    const response = await fetch(
      `http://localhost:8080/user/${loaderData.id}/wallet-recharge`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(walletRecharge),
      }
    );
    if (!(+response.status === 200)) {
      setWarningMessage("Failed");
      setIsFailed(true);
      //Reloading the page in 2 secs
      setTimeout(() => {
        window.location.reload(true);
      }, 2000);
      return false
    } else {
      setWarningMessage("Money Successfully Added");
      setIsFailed(false);

      //calling the rewardchange() from CurrentBalance.js
      props.rewardChange();
      //Reloading the page in 2 secs
      setTimeout(() => {
        window.location.reload(true);
      }, 2000);
      return true
    } 
  }
  return (
    <>
      <Card>
        <form>
          <div className="mb-3">
            <label htmlFor="card" className="form-label">
              Choose Linked Card
            </label>
            {cards.length===0?<p style={{color:"red"}}>Please Add Debit Card for Adding Money</p>:<select
              ref={ref1}
              className="form-control"
              id="card"
              name="card"
              required
            >
              {cards.map((card)=><option key={card.cardNumber} value={card.bankName+" Debit Card xxxx "+String(card.cardNumber).slice(-4)}>
              {card.bankName+" Debit Card xxxx "+String(card.cardNumber).slice(-4)}
              </option>)}
            </select>}
          </div>
          <div className="mb-3">
            <label htmlFor="amount" className="form-label">
              Amount
            </label>
            <input
              ref={ref2}
              type="number"
              className="form-control"
              id="amount"
              name="amount"
              placeholder="Enter the Amount"
              step="any"
              required
            ></input>
          </div>
          <button className="btn btn-primary" disabled={cards.length===0?true:false} onClick={walletRechargeHandler}>
            Add Money
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

//Receiving the props from the CurrentBalace.js
//Wallet Recharge Form
