FROM gitpod/workspace-full

# TODO install podman and do in container builds
RUN sudo apt-get -y update \
    && sudo apt-get -y install build-essential libz-dev zlib1g-dev \
    && mkdir -p ~/.local/share/fonts \
    && mkdir -p ~/.local/share/fonts \
    && cd ~/.local/share/fonts \
    && curl -fLo "Fira Mono Regular Nerd Font Complete Mono" https://github.com/ryanoasis/nerd-fonts/blob/master/patched-fonts/FiraMono/Regular/complete/Fira%20Mono%20Regular%20Nerd%20Font%20Complete%20Mono.otf \
    && curl -fLo "JetBrains Mono Regular Nerd Font Complete Mono" https://github.com/ryanoasis/nerd-fonts/blob/master/patched-fonts/JetBrainsMono/Ligatures/Regular/complete/JetBrains%20Mono%20Regular%20Nerd%20Font%20Complete%20Mono.ttf \
    && cd ~ && mkdir ~/graalvm \
    && wget -O /tmp/graalvm-ce-amd64.tar.gz https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.1.0/graalvm-ce-java11-linux-amd64-21.1.0.tar.gz \
    && tar -xzf /tmp/graalvm-ce-amd64.tar.gz -C ~/graalvm --strip-components=1 \
    && ~/graalvm/bin/gu --auto-yes install native-image

ENV GRAALVM_HOME="${HOME}/graalvm"
ENV JAVA_HOME="${GRAALVM_HOME}"
ENV PATH="${GRAALVM_HOME}/bin:${PATH}"
