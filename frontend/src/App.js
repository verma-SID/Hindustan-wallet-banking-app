import {createBrowserRouter, RouterProvider } from 'react-router-dom';
import WelcomePage from './pages/WelcomePage';
import Error from './pages/Error';
import LoginPage, { action as loginAction} from './pages/LoginPage';
import SignupPage, {action as signupAction} from './pages/SignupPage'
import HomeRootLayout from './pages/Authenticated/HomeRootLayout'
import {loader as authLoader} from './pages/Authenticated/HomeRootLayout'
import CurrentBalance,{loader as currentBalanceLoader} from './pages/Authenticated/CurrentBalance';
import HomePage,{loader as userDetailsLoader} from './pages/Authenticated/HomePage';
import Transactions,{loader as transactionsLoader} from './pages/Authenticated/Transactions';
import Rewards,{loader as rewardsLoader} from './pages/Authenticated/Rewards';
import LinkedCards from './pages/Authenticated/LinkedCards';
import {loader as linkedCardsLoder} from './pages/Authenticated/LinkedCards';
const router=createBrowserRouter([
  {
    path: "/",
    errorElement: <Error />,
    id: "root",
    children:[
      { index: true, element: <WelcomePage /> },
      { path:"login", element: <LoginPage />, action:loginAction},
      { path:"signup", element: <SignupPage />, action:signupAction }
    ]
  },
  {
    path: "/user/:id",
    errorElement: <Error />,
    id: "user",
    element: <HomeRootLayout></HomeRootLayout>,
    loader:authLoader,
    children:[
      {index:true, element:<HomePage></HomePage>, loader:userDetailsLoader},
      {path:"balance", element:<CurrentBalance></CurrentBalance>, loader:currentBalanceLoader},
      {path:"transactions", element:<Transactions></Transactions>, loader:transactionsLoader},
      {path:"rewards", element:<Rewards></Rewards>, loader:rewardsLoader},
      {path:"linked-cards", element:<LinkedCards></LinkedCards>, loader:linkedCardsLoder},
      {path:"help", element:<h1>help</h1>},
    ]
  }
])
function App() {
  return (
    <RouterProvider router={router}></RouterProvider>
  );
}

export default App;
