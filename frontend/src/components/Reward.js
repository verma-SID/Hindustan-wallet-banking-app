export default function Reward(props) {
  return (
    <a className="list-group-item list-group-item-action">
      <div className="d-flex w-100 justify-content-between">
        <h5 className="mb-1">
          <span>&#8377;</span>
          {props.amount.toFixed(2)}
        </h5>
        <small className="text-body-secondary">{props.date}</small>
      </div>
    </a>
  );
}

//Receiving props from the Rewards.js (reward page)
//Reward Component