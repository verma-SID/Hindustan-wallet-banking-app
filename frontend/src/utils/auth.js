export default function tokenLoader() {
    const email = localStorage.getItem("userMail");
    return email;
}
  