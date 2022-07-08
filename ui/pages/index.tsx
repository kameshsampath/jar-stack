import { Alert, AlertIcon, AlertTitle, Container, Heading, StackDivider, VStack } from '@chakra-ui/react';
import type { NextPage } from 'next';
import { FetchFruits } from '../utils/fruitsService';
import FruitsLoading from '../components/loader';
import FruitsList from '../components/fruitsList';

const Home: NextPage = () => {

  const { fruitsData, isLoading, isError } = FetchFruits();

  //console.log(`Fruits Data : ${isError}`);

  if (isLoading) {
    return <FruitsLoading />;
  }

  if (isError) {
    return (
      <Alert status="error">
        <AlertIcon />
        <AlertTitle mr={2}>Error Loading Fruits: {isError}</AlertTitle>
      </Alert>
    );
  }

  return (
    <Container >
      <VStack divider={<StackDivider borderColor="teal.200" />}
        spacing={4}
        align="stretch">
        <Heading as="h1" size="2xl" mb="2">
          Fruits App
        </Heading>
        <FruitsList fruits={fruitsData.fruits} />
      </VStack>
    </Container>
  );
};

export default Home;
