import { Outlet, redirect } from "react-router-dom";
import HomeNavigation from "../../components/HomeNavigation";
import tokenLoader from "../../utils/auth";
export default function RootLayout() {
  return (
    <div data-testid="homenav">
      <HomeNavigation/>
      <main>
        <Outlet />
      </main>
    </div>
  );
}

//Loader Function
export function loader({ request, params }) {
  const id = params.id;
  const token = tokenLoader();
  if (token === id) {
    //By this verification only the corresponding user can access the after login urls.
    return token; //It can also be possible like if(token) return token
  }
  return redirect("/");
}

//Layout for /home pages
