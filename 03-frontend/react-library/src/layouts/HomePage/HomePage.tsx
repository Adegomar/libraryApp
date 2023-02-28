import { Carousel } from "./components/Carousel"
import { ExploreTopBooks } from "./components/ExploreTopBooks"
import { Heros } from "./components/Heros"
import { LibraryServices } from "./components/LibraryServices"

export const HomePage = () => {
    return (/* <></> to Specjalna opcja Reacta która mówi- chce zwrócenia każdego pojedynczego elementu
    ale nie chce tego jako div */
        <> 
            <ExploreTopBooks />
            <Carousel />
            <Heros />
            <LibraryServices />
        </>

    )
}