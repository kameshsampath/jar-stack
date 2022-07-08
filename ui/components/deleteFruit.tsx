import React from "react";
import {
  Button,
  IconButton,
  ModalOverlay,
  Modal,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  useToast
} from "@chakra-ui/react";
import { DeleteIcon } from "@chakra-ui/icons";
import { Fruit } from "../utils/constants";
import { NextPage } from "next";

export interface DeleteFruitsProps {
  fruit: Fruit,
  refreshFn: () => void
}

const DeleteFruit:NextPage<DeleteFruitsProps> = ({fruit,refreshFn}) => {

  const { isOpen, onOpen, onClose } = useDisclosure();
  const toast = useToast();
  const handleDelete = async (data:Fruit) =>{
    console.log(`Deleting data ${JSON.stringify(data)}`);

    const reqOptions = {
      method: 'DELETE'
    };

    const res = await fetch(`/api/fruits/${data.id}`,reqOptions);
    onClose();
    refreshFn();

    if (res.status === 204){
      toast({
        title: `Fruit ${data.name} deleted!`,
        status: "success",
        isClosable: true,
        duration: 3000
      });
    } else {
      toast({
        title: res.statusText,
        status: "error",
        isClosable: true,
        duration: 3000
      });
    }

  };

  return (
    <>
      <IconButton
        colorScheme="red"
        aria-label="Delete Fruit"
        size="md"
        variant="outline"
        onClick={onOpen}
        isRound={true}
        icon={<DeleteIcon />}
      />
      <Modal
        isOpen={isOpen}
        onClose={onClose}
      >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Delete Fruit</ModalHeader>
          <ModalCloseButton />
          <ModalBody pb={6}>
            <p>Do you want to delete the fruit <strong>{`${fruit.name}`}</strong>?</p>
          </ModalBody>
          <ModalFooter>
            <Button
              colorScheme="blue"
              mr={3}
              onClick={() => handleDelete(fruit)}
            >
              Yes
            </Button>
            <Button onClick={onClose}>No</Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default DeleteFruit;
