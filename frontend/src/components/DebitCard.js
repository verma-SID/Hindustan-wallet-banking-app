import { useLoaderData } from "react-router-dom";

export default function DebitCard(props){
    const loaderData=useLoaderData();
    async function deleteHandler() {
        let value=window.confirm("Are You Sure to Delete the Card?")
        if(value===true){
        const deletedCard = {
          cardNumber: props.card,
          bankName: props.bank,
          cardHolder:props.name
        };
    
        //making the HTTP request
        const response = await fetch(
          `http://localhost:8080/user/${loaderData.id}/delete-card`,
          {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(deletedCard),
          }
        );
        if (+response.status === 202) {
            window.location.reload(true);
        }
      }
    }
    return(
    <a className="list-group-item list-group-item-action">
      <div className="d-flex w-100 justify-content-between">
        <h5 className="mb-1">
          {props.bank}
        </h5>
        <p className="mb-1">Card Number: {props.card}</p>
      </div>
      <small className="text-body-secondary" onClick={deleteHandler}>Delete</small>
    </a>
    )
}