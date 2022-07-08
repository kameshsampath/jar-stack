import { Container, Spinner, StackDivider, VStack } from "@chakra-ui/react";
import { NextPage } from "next";

const FruitsLoading: NextPage = () => {
  return (
    <Container>
      <VStack divider={<StackDivider borderColor="teal.200" />}
        spacing={4}
        align="stretch">
        <Spinner
          thickness="4px"
          speed="0.65s"
          emptyColor="gray.200"
          color="teal.500"
          size="xl"
        />
      </VStack>
    </Container>
  );
};

export default FruitsLoading;
