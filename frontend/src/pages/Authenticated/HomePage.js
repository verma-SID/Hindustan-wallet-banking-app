import { useLoaderData } from "react-router-dom";
import img from "../../components/images/Welcome.jpg";
import mobileRecharge from "../../components/images/MobileRecharge.png";
import dthRecharge from "../../components/images/DTHRecharge.png";
import payBills from "../../components/images/BillPayment.png";
import moneyTransfer from "../../components/images/MoneyTransfer.png";
import payRent from "../../components/images/Rent.png";
export default function HomePage() {
  const data = useLoaderData();
  return (
    <>
      <div data-testid="greeting" class="container-welcome">
        {/* fetching firstName and lastName from loader */}
        <h1>
          Hi! {data.firstName} {data.lastName}
        </h1>
        <p>Complete your KYC for more benifits</p>
      </div>
      <div data-testid="features" class="container-features">
        <div class="container text-center">
          <div class="row">
            <div class="col">
              <img
                src={mobileRecharge}
                width={100}
                height={100}
                style={{ padding: "1rem" }}
              ></img>
              <p class="h6">Mobile Recharge</p>
            </div>
            <div class="col">
              <img
                src={dthRecharge}
                width={100}
                height={100}
                style={{ padding: "1rem" }}
              ></img>
              <p class="h6">DTH Recharge</p>
            </div>
            <div class="col">
              <img
                src={payBills}
                width={100}
                height={100}
                style={{ padding: "1rem" }}
              ></img>
              <p class="h6">Pay Bills</p>
            </div>
            <div class="col">
              <img
                src={moneyTransfer}
                width={100}
                height={100}
                style={{ padding: "1rem" }}
              ></img>
              <p class="h6">Money Transfer</p>
            </div>
            <div class="col">
              <img
                src={payRent}
                width={100}
                height={100}
                style={{ padding: "1rem" }}
              ></img>
              <p class="h6">Pay Rent</p>
            </div>
          </div>
        </div>
      </div>
      <div data-testid="instruments" className="container instruments">
        <div style={{ width: "60%" }}>
          <h1 class="display-3">Hindustan Pe Instruments</h1>
          <div className="pay-icon">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="50"
              height="50"
              fill="currentColor"
              class="bi bi-wallet"
              viewBox="0 0 16 16"
            >
              {" "}
              <path d="M0 3a2 2 0 0 1 2-2h13.5a.5.5 0 0 1 0 1H15v2a1 1 0 0 1 1 1v8.5a1.5 1.5 0 0 1-1.5 1.5h-12A2.5 2.5 0 0 1 0 12.5V3zm1 1.732V12.5A1.5 1.5 0 0 0 2.5 14h12a.5.5 0 0 0 .5-.5V5H2a1.99 1.99 0 0 1-1-.268zM1 3a1 1 0 0 0 1 1h12V2H2a1 1 0 0 0-1 1z" />{" "}
            </svg>
            <p>
              Pay with Hindustan Pe<br></br>Wallet
            </p>
          </div>
          <div className="container topay">
            <h1>The Fastest Way To</h1>
            <ul>
              <li class="h5">Recharge</li>
              <li class="h5">Pay Bills</li>
              <li class="h5">Money Transfer</li>
              <li class="h5">Pay Rent</li>
            </ul>
          </div>
          <button class="button-85" type="button">
            Download on PC
          </button>
        </div>
        <img src={img} width={550} height={500}></img>
      </div>
    </>
  );
}

//loader fucntion for fetching the First Name and Last Name
export async function loader({ params }) {
  const id = params.id;
  const response = await fetch(`http://localhost:8080/user/${id}/user-details`);
  if (response.status !== 200) {
    //do error handling by throw
    return null;
  } else {
    const resData = await response.json();
    return resData;
  }
}

//Home Page
