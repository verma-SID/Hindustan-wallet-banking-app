import { useLoaderData } from "react-router-dom";
import Reward from "../../components/Reward";

export default function Rewards() {
  const data = useLoaderData();
  //revere the array to show the rewards by most recent
  const reversedRewardList = [...data.resData.rewardList].reverse();

  //calling the /wallet-recharge API for transfering the cashback amount
  async function transferToWallet(event) {
    event.preventDefault();
    let value=window.confirm("Are You Sure?")
    if(value===true){
    const walletRecharge = {
      card: "Cashback Transfer",
      amount: +data.resData.amount,
    };
    const response = await fetch(
      `http://localhost:8080/user/${data.id}/wallet-recharge`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(walletRecharge),
      }
    );
    if (!(+response.status === 200)) {
      window.alert("Transfer Failed");
    }
    window.location.reload(true);
  }
}
  return (
    <div className="container">
      <div style={{ borderBottom: "1px solid black", marginBottom: "1rem" }}>
        <h1 style={{ marginTop: "2rem", marginBottom: "1rem" }}>
          My Rewards: <span>&#8377;</span>
          {data.resData.amount.toFixed(2)}
        </h1>
        <button
          style={{ marginBottom: "1rem" }}
          class="btn btn-primary"
          onClick={transferToWallet}
          disabled={reversedRewardList.length===0?true:false}
        >
          Transfer to Wallet
        </button>
      </div>
      <div class="list-group">
        {reversedRewardList.length===0?<p>No Rewards Yet</p>:reversedRewardList.map((reward) => (
          <Reward
            key={reward.id}
            amount={reward.amount}
            date={reward.date}
          ></Reward>
        ))}
      </div>
    </div>
  );
}

//Loader function for fetching the Rewards List
export async function loader({ params }) {
  const id = params.id;
  const response = await fetch(`http://localhost:8080/user/${id}/rewards`);
  if (response.status !== 200) {
    //do error handling by throw
    return null;
  } else {
    const resData = await response.json();
    //also sending the id in loader data, id will be used in calling the wallet recharge API for Transfering the Cashback to Wallet Amount
    return { id, resData };
  }
}

//Rewards Page
