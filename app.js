document.addEventListener('DOMContentLoaded', () => {
    // State management
    const state = {
        documents: JSON.parse(localStorage.getItem('scanmate_docs') || '[]'),
        currentScreen: 'home',
        stream: null
    };

    // UI Elements
    const screens = document.querySelectorAll('.screen');
    const navItems = document.querySelectorAll('.nav-item');
    const documentList = document.getElementById('document-list');
    const documentCountLabel = document.getElementById('document-count');
    const heroStats = document.getElementById('hero-stats');

    // Navigation
    function navigateTo(screenId) {
        screens.forEach(s => s.classList.remove('active'));
        document.getElementById(`${screenId}-screen`).classList.add('active');
        state.currentScreen = screenId;

        // Update Nav
        navItems.forEach(item => {
            if (item.dataset.screen === screenId) {
                item.classList.add('active');
            } else {
                item.classList.remove('active');
            }
        });

        // If leaving camera, stop stream
        if (screenId !== 'scan' && state.stream) {
            state.stream.getTracks().forEach(track => track.stop());
            state.stream = null;
        }
    }

    navItems.forEach(item => {
        item.addEventListener('click', () => {
            const screen = item.dataset.screen;
            if (screen === 'home') navigateTo('home');
            // Other screens can be added here
        });
    });

    // Document Management
    function renderDocuments() {
        if (state.documents.length === 0) {
            documentList.innerHTML = `
                <div class="empty-state">
                    <span class="material-icons-outlined">description</span>
                    <p>No documents yet</p>
                    <button class="btn btn-primary" id="empty-scan-btn">Scan now</button>
                </div>
            `;
            document.getElementById('empty-scan-btn')?.addEventListener('click', startScan);
            documentCountLabel.textContent = '0 shown';
            heroStats.textContent = 'Ready to scan your first document';
        } else {
            documentCountLabel.textContent = `${state.documents.length} shown`;
            heroStats.textContent = state.documents.length === 1
                ? 'You have 1 document'
                : `You have ${state.documents.length} documents`;

            documentList.innerHTML = state.documents.map(doc => `
                <div class="document-item">
                    <div class="doc-thumb">
                        <span class="material-icons-outlined">insert_drive_file</span>
                    </div>
                    <div class="doc-info">
                        <div class="doc-title">${doc.title}</div>
                        <div class="doc-meta">${doc.date} • ${doc.pages} pages</div>
                    </div>
                    <span class="material-icons-outlined">chevron_right</span>
                </div>
            `).join('');
        }
    }

    function addDocument(title, pages = 1) {
        const newDoc = {
            id: Date.now(),
            title: title || `Scan ${new Date().toLocaleDateString()}`,
            date: new Date().toLocaleDateString(),
            pages: pages,
            timestamp: Date.now()
        };
        state.documents.unshift(newDoc);
        localStorage.setItem('scanmate_docs', JSON.stringify(state.documents));
        renderDocuments();
    }

    // Camera Logic
    async function startScan() {
        try {
            state.stream = await navigator.mediaDevices.getUserMedia({
                video: { facingMode: 'environment' }
            });
            const video = document.getElementById('camera-preview');
            video.srcObject = state.stream;
            navigateTo('scan');
        } catch (err) {
            alert('Camera access denied or not available');
            console.error(err);
        }
    }

    document.getElementById('scan-btn-hero').addEventListener('click', startScan);
    document.getElementById('close-camera').addEventListener('click', () => navigateTo('home'));

    document.getElementById('take-photo').addEventListener('click', () => {
        // Mock photo taking
        const flash = document.createElement('div');
        flash.style.position = 'fixed';
        flash.style.top = '0';
        flash.style.left = '0';
        flash.style.width = '100%';
        flash.style.height = '100%';
        flash.style.backgroundColor = 'white';
        flash.style.zIndex = '1000';
        document.body.appendChild(flash);

        setTimeout(() => {
            flash.remove();
            addDocument();
            navigateTo('home');
        }, 100);
    });

    // Search Logic (Mock)
    document.getElementById('search-input').addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const items = document.querySelectorAll('.document-item');
        items.forEach(item => {
            const title = item.querySelector('.doc-title').textContent.toLowerCase();
            if (title.includes(query)) {
                item.style.display = 'flex';
            } else {
                item.style.display = 'none';
            }
        });
    });

    // Initial Render
    renderDocuments();
});
