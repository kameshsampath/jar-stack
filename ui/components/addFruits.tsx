import { Button, FormControl, FormLabel, IconButton, Input, ModalOverlay, Select, useDisclosure } from "@chakra-ui/react";
import React from "react";
import { useForm } from "react-hook-form";
import { FRUIT_SEASON_OPTIONS } from '../utils/constants';
import {
  Alert,
  AlertIcon,
  AlertTitle,
  Modal,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useToast
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";
import { NextPage } from "next";

export interface AddFruitsProps {
  refreshFn: () => void
}

const AddFruit:NextPage<AddFruitsProps> = ({refreshFn}) => {

  const { isOpen, onOpen, onClose } = useDisclosure();

  const { register, handleSubmit, formState: { errors } } = useForm();
  const toast = useToast( );

  const onSubmit = async (data: any) => {
    //console.log(`Submitting data ${JSON.stringify(data)}`);
    const reqOptions = {
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      },
      method: 'POST'
    };

    const res = await fetch("/api/fruits/save",reqOptions);


    if (res.status < 300 ){
      toast({
        title: `Fruit ${data.name} saved!`,
        status: "success",
        isClosable: true,
        duration: 3000
      });
      refreshFn();
    } else {
      toast({
        title: res.statusText,
        status: "error",
        isClosable: true,
        duration: 3000
      });
    }

    onClose();
  };

  const AlertPop = (props: any) => {
    return (
      <Alert status="error">
        <AlertIcon />
        <AlertTitle mr={2}>{props.title}</AlertTitle>
      </Alert>
    );
  };

  return (

    <>
      <IconButton
        colorScheme="teal"
        aria-label="New Fruit"
        size="md"
        variant="outline"
        onClick={onOpen}
        isRound={true}
        icon={<AddIcon />}
      />
      <Modal
        isOpen={isOpen}
        onClose={onClose}
        isCentered={true}
      >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Add a new Fruit</ModalHeader>
          <ModalCloseButton />
          {errors.name && <AlertPop title={errors.name.message} />}
          {errors.season && <AlertPop title={errors.season.message} />}
          <ModalBody pb={6}>
            <FormControl>
              <FormLabel>Fruit name</FormLabel>
              <Input placeholder="Enter a fruit name"
                {...register("name", {
                  required: "Please enter a valid fruit name"
                }
                )} />
            </FormControl>
            <FormControl mt={4}>
              <FormLabel>Season</FormLabel>
              <Select placeholder="Select the season"
                {...register("season", { required: "Please select a season" })}>
                {FRUIT_SEASON_OPTIONS.map((option, index) => (
                  <option key={index} value={option.value}>{option.label}</option>
                ))}
              </Select>
            </FormControl>
          </ModalBody>

          <ModalFooter>
            <Button
              colorScheme="blue"
              mr={3}
              type="submit"
              variant="ghost"
              onClick={handleSubmit(onSubmit)}>
              Save
            </Button>
            <Button
              colorScheme="blue"
              variant="ghost"
              mr={3}
              onClick={onClose}
            >Cancel</Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default AddFruit;
