import DebitCard from "../../components/DebitCard";
import DebitCardForm from "../../components/DebitCardForm";
import debitCard from "../../components/images/DebitCard.png";
import { useLoaderData } from "react-router-dom";
export default function LinkedCards(){
    const data = useLoaderData();
    const cards=[...data.resData.cardList];
    return(
    <div className="container">
      <div data-testid="heading" style={{ borderBottom: "1px solid black", marginBottom: "1rem" }}>
        <h1 style={{ marginTop: "2rem", marginBottom: "1rem"}}>
          Linked Cards
        </h1>
      </div>
      <h1 class="display-6">Add Card</h1>
      <div data-testid="img-form" style={{display:"flex",flexDirection:"row", justifyContent:"space-between",borderBottom: "1px solid black", marginBottom: "1rem"}}>
        <img src={debitCard} width={350} height={350}></img>
        <div style={{width:"60%", paddingTop:"1.5rem"}}>
        <DebitCardForm></DebitCardForm>
        </div>
      </div>
      <h1 class="display-6">Your Cards</h1>
      <div data-testid="cards" class="list-group" style={{marginTop:"1rem"}}>
        {cards.length===0?<p>No Cards Present</p>:cards.map((card)=><DebitCard key={card.cardNumber} bank={card.bankName} card={card.cardNumber} name={card.cardHolder}></DebitCard>)}
      </div>
    </div>
    )
}
export async function loader({ params }) {
    const id = params.id;
    const response = await fetch(`http://localhost:8080/user/${id}/cards`);
    if (response.status !== 200) {
      //do error handling by throw
      return null;
    } else {
      const resData = await response.json();
      return {resData,id};
    }
  }