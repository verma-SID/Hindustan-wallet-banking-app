import { useState } from "react";
import { useLoaderData } from "react-router-dom";
import Balance from "../../components/Balance";
import SendMoneyForm from "../../components/SendMoneyForm";
import WalletRechargeForm from "../../components/WalletRechargeForm";
export default function CurrentBalance() {
  //useState() for Reward Animation
  const [isReward, setIsReward] = useState(false);
  const data = useLoaderData();

  //called in WalletRecharge form if successful transaction happens and change the reward state
  function changeRewardState() {
    setIsReward(true);
  }
  return (
    <div className="container" data-testid="elements">
      <Balance amount={data.resData1.amount}></Balance>
      <div className="money-actions">
        <div className="send-money">
          <SendMoneyForm></SendMoneyForm>
        </div>
        <div className="wallet-recharge">
          <WalletRechargeForm
            rewardChange={changeRewardState}
          ></WalletRechargeForm>
        </div>
      </div>
      {isReward && (
        <div class="animate seven" style={{ paddingLeft: "25rem" }}>
          <span>N</span>
          <span>e</span>
          <span>w</span>&nbsp;
          <span>R</span>
          <span>e</span>
          <span>w</span>
          <span>a</span>
          <span>r</span>
          <span>d</span>&nbsp;
          <span>E</span>
          <span>a</span>
          <span>r</span>
          <span>n</span>
          <span>e</span>
          <span>d</span>
        </div>
      )}
    </div>
  );
}

//loader for fetching the current balance
export async function loader({ params }) {
  const id = params.id;
  const response1 = await fetch(
    `http://localhost:8080/user/${id}/current-balance`
  );
  const response2=await fetch(
    `http://localhost:8080/user/${id}/cards`
  );
  if (response1.status&&response2.status !== 200) {
    //do error handling by throw
    return null;
  } else {
    const resData1 = await response1.json();
    const resData2 = await response2.json();
    return { resData1,resData2, id };
  }
}
//Current Balance page