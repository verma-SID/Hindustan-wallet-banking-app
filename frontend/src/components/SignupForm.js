import { Form, useNavigate, useNavigation } from "react-router-dom";

export default function SignupForm() {
  //using useLoader() to for error messages
  const navigate = useNavigate();
  const navigation=useNavigation();
  const isSubmitting=navigation.state==="submitting";
  function onLoginClickHandler() {
    navigate("/login");
  }
  return (
    <>
    <Form method="post" class="row g-3">
      <div class="col-md-6">
        <label for="validationDefault01" class="form-label">
          First Name
        </label>
        <input
          type="text"
          class="form-control"
          id="validationDefault01"
          name="firstName"
          placeholder="First Name"
          required
        />
      </div>
      <div class="col-md-6">
        <label for="validationDefault02" class="form-label">
          Last Name
        </label>
        <input
          type="text"
          class="form-control"
          id="validationDefault02"
          name="lastName"
          placeholder="Last Name"
          required
        />
      </div>
      <div class="col-md-12">
        <label for="validationDefaultUsername" class="form-label">
          Email Address
        </label>
        <input
          type="email"
          class="form-control"
          id="validationDefaultUsername"
          placeholder="Email"
          name="email"
          required
        />
      </div>
      <div class="col-md-12">
        <label for="validationDefault03" class="form-label">
          Password
        </label>
        <input
          type="text"
          class="form-control"
          id="validationDefault03"
          name="password"
          placeholder="Password"
          required
        />
      </div>
      <div class="col-md-12">
        <button class="btn btn-primary">{isSubmitting?"Signing up...":"Signup"}</button>
        <button class="btn btn-primary" onClick={onLoginClickHandler}>
          Login
        </button>
      </div>
    </Form>
    </>
  );
}

//Signup Form